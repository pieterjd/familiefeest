import { NgModule } from '@angular/core';

import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "./registration/registration.component";
import {RegistrationOverviewComponent} from "./registration-overview/registration-overview.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path:'registration/:eventCode', component: RegistrationComponent},
  {path:'registration', component: RegistrationComponent},
  {path:'registration-overview/:eventId', component: RegistrationOverviewComponent},
  {path:'', component: HomeComponent}

  ]
;


@NgModule({
  declarations: [],
  imports:[
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})


export class AppRoutingModule { }
