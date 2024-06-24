import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginRequest, RegisterRequest } from '../models/forms.model';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  constructor() { }

  initLoginForm(): FormGroup<LoginRequest>{
    return new FormGroup({
      email: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.email],
        nonNullable: true,
      }),
      password: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.minLength(8), 
          Validators.maxLength(30)],
        nonNullable: true,
      }),
    });
  }

  initRegisterForm(): FormGroup<RegisterRequest>{
    return new FormGroup({
      firstname: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.minLength(8), 
          Validators.maxLength(30)],
        nonNullable: true,
      }),
      lastname: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.minLength(8), 
          Validators.maxLength(30)],
        nonNullable: true,
      }),
      email: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.email],
        nonNullable: true,
      }),
      password: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.minLength(8), 
          Validators.maxLength(30)],
        nonNullable: true,
      }),
      repeatedPassword: new FormControl('', {
        validators: [
          Validators.required, 
          Validators.minLength(8), 
          Validators.maxLength(30)],
        nonNullable: true,
      }),
    });
  }




  getErrorMessage(control: FormControl): string{
    if(control.hasError('required') && control.touched){
      return 'Ta kontrolka jest wymagana'
    }
    if(control.hasError('minlength') && control.touched){
      return `Minimalna ilość znaków: ${control.errors?.['minlength']?.requiredLength}.`;
    }
    if(control.hasError('maxlength') && control.touched){
      return `Maksymalna ilość znaków: ${control.errors?.['maxlength']?.requiredLength}.`;
    }
    if(control.hasError('email') && control.touched){
      return 'Nie poprawny email';
    }
    return '';
  }
}
