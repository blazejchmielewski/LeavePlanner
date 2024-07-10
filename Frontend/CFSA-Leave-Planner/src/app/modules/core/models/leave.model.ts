export interface LeaveData {
    startDate: Date,
    endDate: Date,
    type: string
}

export enum LeaveType {
    ANNUAL_LEAVE = 'ANNUAL_LEAVE',
    SICK_LEAVE = 'SICK_LEAVE',
    MATERNITY_LEAVE = 'MATERNITY_LEAVE',
    PATERNITY_LEAVE = 'PATERNITY_LEAVE',
    UNPAID_LEAVE = 'UNPAID_LEAVE',
    OTHER = 'OTHER'
}

export interface UsersToSwitch {
    firstname: string;
    lastname: string;
}