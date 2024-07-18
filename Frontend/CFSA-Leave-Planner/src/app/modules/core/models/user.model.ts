export interface UserAllData {
    id: number;
    uuid: string;
    firstname: string;
    lastname: string;
    email: string;
    department: string;
    role: string;
}

export interface UserDetails{
    name: string,
    email: string,
    role: string,
    department: string,
    status: boolean,
    createdAt: Date,
    deactivatedAt: Date,
}

export interface Authority {
    authority: string;
}

export interface EditUserData {
    firstname: string;
    lastname: string;
    email: string;
    department: string;
}