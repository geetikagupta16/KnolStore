
export interface EmployeeTransactionDetails {
  employeeId: number;
  employeeName: string;
  totalAmount: number;
  billDetails: Bill[];
}

export interface EmployeeTransactionData {
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
  employee: EmployeeTransactionDetails;
}

export interface Bill{
  itemName:string
  date:string
  quantity:number
  price:number
  amount:number
}
