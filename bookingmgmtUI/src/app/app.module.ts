import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AllBookingsComponent } from './all-bookings/all-bookings.component';
import { AddBookingComponent } from './add-booking/add-booking.component';
import { FindBookingComponent } from './find-booking/find-booking.component';
import { GetTicketComponent } from './get-ticket/get-ticket.component';
import { FormsModule } from '@angular/forms';
import { CancelBookingComponent } from './cancel-booking/cancel-booking.component';
import { BookingServiceService } from './services/booking-service.service';

@NgModule({
  declarations: [
    AppComponent,
    AllBookingsComponent,
    AddBookingComponent,
    FindBookingComponent,
    GetTicketComponent,
    CancelBookingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [BookingServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
