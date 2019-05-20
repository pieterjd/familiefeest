import { NgModule } from '@angular/core';

import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "./registration/registration.component";
import {RegistrationOverviewComponent} from "./registration-overview/registration-overview.component";

const routes: Routes = [
  {path:'registration', component: RegistrationComponent},
  {path:'registration-overview/:eventId', component: RegistrationOverviewComponent},

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
