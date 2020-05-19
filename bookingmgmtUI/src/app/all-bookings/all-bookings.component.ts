import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';

@Component({
  selector: 'app-all-bookings',
  templateUrl: './all-bookings.component.html',
  styleUrls: ['./all-bookings.component.css']
})
export class AllBookingsComponent implements OnInit {

  bookings:BookingResponse[]=[];
  __service:BookingServiceService;
  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
    this.__service.fetchAllBookings().subscribe(booking =>{this.bookings=booking});
  }

}
