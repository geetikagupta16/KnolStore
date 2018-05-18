export interface Item {
  itemId: number;
  itemName: string;
  itemPrice: number;
}

export interface ItemData {
  data: ItemDetails;
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

export interface ItemDetails{
  items: Item[];
}