export interface LeaveData {
    startDate: Date,
    endDate: Date,
    type: string,
    userUuid: string
}

export interface UuidObject{
    uuid: string
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
    lastUpdateDate: Date,
    settledByReplacerDate: Date,
    settledByAcceptorDate: Date
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
    APPROVED_BY_REPLACER = 'APPROVED_BY_REPLACER',
    APPROVED_BY_ACCEPTOR = 'APPROVED_BY_ACCEPTOR',
    REJECTED_BY_REPLACER = 'REJECTED_BY_REPLACER',
    REJECTED_BY_ACCEPTOR = 'REJECTED_BY_ACCEPTOR',
    IN_PROGRESS = 'IN_PROGRESS',
    CANCELLED = 'CANCELLED'
}

export interface UsersToSwitch {
    uuid: string
    firstname: string;
    lastname: string;
}

export interface Dayoff {
    holyName: String;
    dayOff: Date;
}

