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
   a :AddItem = {
     itemId:0,
     itemName:'',
      price:null}

  addedItem: AddItem[]=[];

  constructor(public addItemService: AdditemService) { }
  ngOnInit() {
  }

  postItem(){
   // console.log(this.abc.price, this.abc.itemName )
   //  console.log(this.itemprice)
   //  console.log(this.itemvalue)

    let itemToBeAdded:AddItem ={
      itemId:this.a.itemId,
      itemName:this.a.itemName,
      price:this.a.price
    };
    this.addedItem.push(itemToBeAdded);
    console.log(this.addedItem)
    this.addItemService.postItemInformation(this.addedItem).subscribe(data =>{
        console.log(data)
        alert("Item added to the inventory");
        this.addedItem = [];

      },
      error => alert("Something Went Wrong"))

  }


}





