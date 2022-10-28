export interface IEmployee {
    employeeId?: number;
    login?: string;
    firstName?: string | null;
    lastName?: string | null;
    email?: string;
    phoneNumber?: number | null;
    hireDate?: Date | null;
    salary?: number | null;
    commissionPct?: number | null;
    activated?: boolean;
    langKey?: string;
    authorities?: string[];
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
  }
  
  export class Employee implements IEmployee {
    constructor(
      public employeeId?: number,
      public login?: string,
      public firstName?: string | null,
      public lastName?: string | null,
      public email?: string,
      public phoneNumber?: number | null,
      public hireDate?: Date | null,
      public salary?: number | null,
      public commissionPct?: number | null,
      public activated?: boolean,
      public langKey?: string,
      public authorities?: string[],
      public createdBy?: string,
      public createdDate?: Date,
      public lastModifiedBy?: string,
      public lastModifiedDate?: Date
    ) {}
  }
  