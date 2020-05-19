
export class BookingDetailsResponse{
    bookingId:number;
    movieId:number;
    showId:number;
    bookingDate:Date;
    transactionId:number;
    totalCost:number;
    seatIds:number[];
   
    constructor(bookingId:number, movieId:number, showId:number, bookingDate:Date, transactionId:number, totalCost:number, seatIds:number[]){
        this.bookingId=bookingId;
        this.movieId=movieId;
        this.showId=showId;
        this.bookingDate=bookingDate;
        this.transactionId=transactionId;
        this.totalCost=totalCost;
        this.seatIds=seatIds;
     
    }
}