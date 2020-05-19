import { Component, OnInit } from '@angular/core';
import { Show } from '../model/show';
import { Movie } from '../model/movie';
import { Theater } from '../model/theater';
import { Screen } from '../model/screen';
import { Booking } from '../model/booking';
import { BookingModel } from '../model/bookingModel';
import { BookingServiceService } from '../services/booking-service.service';
import { BookingResponse } from '../dto/bookingresponse';
import { Observable } from 'rxjs';
import { BookingDetailsResponse } from '../dto/bookingdetailsresponse';

@Component({
  selector: 'app-add-booking',
  templateUrl: './add-booking.component.html',
  styleUrls: ['./add-booking.component.css']
})
export class AddBookingComponent implements OnInit {

  _bookingModel=new BookingModel("",[]);

  __service:BookingServiceService;

  booking:BookingResponse=null;
  seatShown=false;
  showbooking=false;

  cityList=["Delhi","Mumbai","Banglore","Chennai"];
  selectedTheaterList:Array<Theater>=[];
  selectedMovieList:Array<Movie>=[];
  selectedScreenList:Array<Screen>=[];
  selectedShowList:Array<Show>=[];
  selectedSeats:number[]=[];
  seatIds:Array<number>=[];

  constructor(__service:BookingServiceService) {  
    this.__service=__service;
  }

  ngOnInit(): void {
  }

  getTheater(){
    let theaterList=this.__service.getTheaterList();
    this.selectedTheaterList=[];
    let city=this._bookingModel.city;
    theaterList.forEach(theater=>{
        if(theater.theaterCity===city){
          this.selectedTheaterList.push(theater);
        }
    });
  }
  
  getMovieAndScreen(event:any){
    this.selectedMovieList=this.__service.getMovieList();
    this.selectedScreenList=[];
    let theaterList=this.__service.getTheaterList();
    let theaterId=event.target.value;
    theaterList.forEach(theater =>{
      if(theater.theaterId==theaterId){
        this.selectedScreenList=theater.screenList;
      }
    });
  }

  getShow(event:any){
    this.selectedShowList=[];
    let movieList=this.__service.getMovieList();
    let showList=this.__service.getShowList();
    let movieId=event.target.value;
    let movieName="";
    movieList.forEach(movie =>{
      if(movie.movieId==movieId){
        movieName=movie.movieName;
      }
    });
    showList.forEach(show =>{
      if(show.movieName==movieName){
        this.selectedShowList.push(show);
      }
    });
  }

  getSeats(event:any){
    this.seatIds=[];
    let showId=event.target.value;
    this.selectedShowList.forEach(show =>{
      if(showId==show.showId){
        this.seatIds=show.seatIds;
        //this._bookingModel.selectedSeats=show.seatIds;
      }
    });
    this.seatShown=true;
  }

  submit(bookingForm:any){
    this.selectedSeats=[];
    let bookingDetails= bookingForm.value;
    let selectedSeatIds=this._bookingModel.selectedSeats;
    this.seatIds.forEach(seat =>{
      if(selectedSeatIds[seat]==true){
        this.selectedSeats.push(seat);
      }
    });
    let booking:Booking=new Booking(bookingDetails.movie,bookingDetails.show,bookingDetails.screen,
      bookingDetails.paymentMethod,this.selectedSeats);

    let result:Observable<BookingDetailsResponse> = this.__service.addBooking(booking);
    result.subscribe((bookingResp:BookingResponse) =>{
      this.booking=bookingResp;
    },
      err =>{
        console.log("Error "+err);
      });
    
    this.showbooking=true;
  }
}
