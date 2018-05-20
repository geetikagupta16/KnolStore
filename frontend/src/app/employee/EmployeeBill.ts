export class EmployeeBill{
  empId: number;
  itemsPurchased: ItemPurchased[];
  transactionDate:string;
  total:number;
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
