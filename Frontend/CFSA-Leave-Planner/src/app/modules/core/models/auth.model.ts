export interface IUser {
    email: string;
    role: string;
}

export class User implements IUser{
    constructor(
        public email: string, 
        public role: string
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