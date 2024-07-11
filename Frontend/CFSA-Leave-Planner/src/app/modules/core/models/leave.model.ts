export interface LeaveData {
    startDate: Date,
    endDate: Date,
    type: string,
    userUuid: string
}

export interface LeaveDataDetails {
    leaveUuid: string,
    startDate: Date;
    endDate: Date;
    type: string;
    declaringUser: string;
    replacementUser: string;
}

export interface LeaveDataDetailsExtended {
    leaveUuid: string,
    startDate: Date;
    endDate: Date;
    type: string;
    declaringUser: string;
    replacementUser: string;
    status: string,
    creationDate: Date,
    lastUpdateDate: Date
}

export enum LeaveType {
    ANNUAL_LEAVE = 'ANNUAL_LEAVE',
    SICK_LEAVE = 'SICK_LEAVE',
    MATERNITY_LEAVE = 'MATERNITY_LEAVE',
    PATERNITY_LEAVE = 'PATERNITY_LEAVE',
    UNPAID_LEAVE = 'UNPAID_LEAVE',
    OTHER = 'OTHER'
}

export enum LeaveStatus {
    PENDING = 'PENDING',
    APPROVED = 'APPROVED',
    REJECTED = 'REJECTED',
    CANCELLED = 'CANCELLED'
}

export interface UsersToSwitch {
    uuid: string
    firstname: string;
    lastname: string;
}