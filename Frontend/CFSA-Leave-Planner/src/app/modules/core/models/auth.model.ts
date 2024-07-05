export interface IUser {
    email: string;
    role: string;
    token: string;
}

export class User implements IUser{
    constructor(
        public email: string, 
        public role: string,
        public token: string
    ){}
}



export interface LoginData {
    email: string;
    password: string;
}

export interface RegisterData {
    firstname: string;
    lastname: string;
    email: string;
    password: string;
}

export interface AuthResponse {
    message : string;
    code : string;
    timestamp: string;
}

export interface LoggedInResponse extends Omit<AuthResponse, 'message'>{
    message: boolean;
}

export interface ToResetPasswordData {
    email : string;
}

export interface ChangePasswordData {
    uid: string;
    password: string;
}
