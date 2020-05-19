import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddBookingComponent } from './add-booking/add-booking.component';
import { AllBookingsComponent } from './all-bookings/all-bookings.component';
import { FindBookingComponent } from './find-booking/find-booking.component';
import { GetTicketComponent } from './get-ticket/get-ticket.component';
import { CancelBookingComponent } from './cancel-booking/cancel-booking.component';

const routes: Routes = [
  {path:'addBooking',component:AddBookingComponent},
  {path:'bookings',component:AllBookingsComponent},
  {path:'findBooking',component:FindBookingComponent},
  {path:'getTicket',component:GetTicketComponent},
  {path:'cancelBooking', component:CancelBookingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }