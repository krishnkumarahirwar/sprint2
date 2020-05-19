import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-find-booking',
  templateUrl: './find-booking.component.html',
  styleUrls: ['./find-booking.component.css']
})
export class FindBookingComponent implements OnInit {

 
  booking:BookingResponse=null;
  show:boolean=false;
  errorShow=false;
  bookings:Array<BookingResponse>=[];
  __service:BookingServiceService;
  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
  }

  getBooking(searchBookingForm:any){
    let bookingId=searchBookingForm.value.bookingId;
    let response:Observable<BookingResponse>=this.__service.getBooking(bookingId);
    response.subscribe((booking:BookingResponse) =>{
        this.booking=booking;
        this.show=true;
        this.errorShow=false;
    },
      error =>{
        this.errorShow=true;
        this.show=false;
        console.log("Error "+error)
      });
  }
}
