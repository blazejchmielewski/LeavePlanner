import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { UserDetails } from '../../../models/user.model';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit{

  userDetails!: UserDetails;

  constructor(private userService: UserService){}
  
  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails(){
    this.userService.getUserDetails().subscribe({
      next: (resp)=>{
        if(resp){
          this.userDetails = resp
        }
        
      }, error: (err) => {
        console.log(err)
      }
    })
  }

  translateStatus(status: boolean){
    switch (status){
      case true: return 'Aktywny';
      case false: return 'Nie aktywny'
    }
  }
}
