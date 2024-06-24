import { FormControl } from "@angular/forms";

export interface LoginRequest{
    email: FormControl<string>;
    password: FormControl<string>;
}

export interface RegisterRequest{
    firstname: FormControl<string>;
    lastname: FormControl<string>;
    email: FormControl<string>;
    password: FormControl<string>;
    repeatedPassword: FormControl<string>
}