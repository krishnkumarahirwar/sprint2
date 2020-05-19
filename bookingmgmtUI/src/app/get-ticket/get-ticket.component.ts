import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';
import { Observable } from 'rxjs';
import { tick } from '@angular/core/testing';

@Component({
  selector: 'app-get-ticket',
  templateUrl: './get-ticket.component.html',
  styleUrls: ['./get-ticket.component.css']
})
export class GetTicketComponent implements OnInit {
  ticket:Ticket=null;
  show:boolean=false;
  errorShow=false;
  bookings:Array<BookingResponse>=[];
  __service:BookingServiceService;
  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
  }

  getTicket(searchTicketForm:any){
    let bookingId=searchTicketForm.value.bookingId;

    let result:Observable<Ticket> = this.__service.getTicket(bookingId);
    result.subscribe((ticket:Ticket) =>{
      this.ticket=ticket;
      this.show=true;
      this.errorShow=false;
    },
    error =>{
      console.log("Error "+error);
      this.show=false;
      this.errorShow=true;
      
    });
  }

}
