export interface UserAllData {
    id: number;
    uuid: string;
    firstname: string;
    lastname: string;
    email: string;
    department: string;
    role: string;
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