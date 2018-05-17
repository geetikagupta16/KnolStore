
export interface EmployeeDetails {
  employeeId: number;
  employeeName: string;
}

export interface EmployeeData {
  data: Employee;
  status: number;
}

export interface ErrorLogger {
  status: number;
  error: ErrorDetails;
}

export interface ErrorDetails {
  errorId: string;
  errorMsg: string;
}

export interface Employee{
  employee: EmployeeDetails[];
}
