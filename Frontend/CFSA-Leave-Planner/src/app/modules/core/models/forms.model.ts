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
    department: FormControl<string>
}

export interface EditUserRequest{
    firstname: FormControl<string>;
    lastname: FormControl<string>;
    email: FormControl<string>
    department: FormControl<string>
}

export interface ToGetPasswordRecoveryRequest{
    email: FormControl<string>;
}

export interface PasswordRecoveryRequest{
    password: FormControl<string>;
    repeatedPassword: FormControl<string>
}

export interface LeaveRequest{
    startDate: FormControl<Date>;
    endDate: FormControl<Date>;
    type: FormControl<string>;
    userUuid: FormControl<string>
}

export interface DayoffRequest {
    holyName: FormControl<string>;
    dayOff: FormControl<Date>;
}
