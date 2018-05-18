export class EmployeeBill{
  empId: number;
  transactionDate:string;
  total:number;
  itemsPurchased: ItemPurchased[];
  constructor(){}
}
export class ItemPurchased{
  itemId: number;
  itemName: string;
  price:number;
  itemQuantity:number;
  amount:number;
  constructor(){}
}
