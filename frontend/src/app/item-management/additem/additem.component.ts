import { Component, OnInit } from '@angular/core';
import {AddItem} from "./AddItem";
import {AdditemService} from "./additem.service";
declare var toastr:any;
@Component({
  selector: 'app-additem',
  templateUrl: './additem.component.html',
  styleUrls: ['./additem.component.css']
})

export class AdditemComponent implements OnInit {

  constructor(public addItemService: AdditemService) { }

  ngOnInit() {
  }
  itemidvalue:number;
itemvalue:string;
  itemprice: number;
  addedItem: AddItem[]=[];

  postItem(){
    let itemToBeAdded:AddItem ={
      itemId:this.itemidvalue,
      itemName:this.itemvalue,
      itemPrice:this.itemprice
    };
    this.addedItem.push(itemToBeAdded);
    this.addItemService.postItem(this.addedItem).subscribe(data =>{
      toastr.success("Item added to the inventory");
      this.addedItem = [];

    },
      error => toastr.warning("Something Went Wrong"))

    }


  }





