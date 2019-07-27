import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

// angular material
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
// import widgets
import {
  MatCardModule,
  MatToolbarModule,
  MatStepperModule,
  MatFormFieldModule,
  MatButtonModule,
  MatInputModule,
  MatSelectModule,
  MatListModule,
  MatIconModule,
  MatDialogModule,
  MatDialog,
  MatTooltipModule, MatSnackBarModule
} from "@angular/material";

import {AppComponent} from './app.component';
import {RegistrationComponent} from './registration/registration.component';
import {AppRoutingModule} from './app-routing.module';
import {EventRegistrationService} from "./event-registration.service";
import {EventcodeCheckerComponent} from './eventcode-checker/eventcode-checker.component';
import {AddMenuDialog, MenulistComponent} from './menulist/menulist.component';
import {ConfirmationComponent} from './confirmation/confirmation.component';
import {AddPaymentDialog, RegistrationOverviewComponent} from './registration-overview/registration-overview.component';
import {HomeComponent} from './home/home.component';
import {MailService} from "../service/mail.service";
import { HeaderComponent } from './header/header.component';
import { StatsComponent } from './stats/stats.component';


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    EventcodeCheckerComponent,
    MenulistComponent,
    AddMenuDialog,
    ConfirmationComponent,
    RegistrationOverviewComponent,
    AddPaymentDialog,
    HomeComponent,
    HeaderComponent,
    StatsComponent
  ],
  entryComponents: [
    AddMenuDialog,
    AddPaymentDialog
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatToolbarModule,
    MatStepperModule,
    MatFormFieldModule,
    AppRoutingModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatListModule,
    MatIconModule,
    MatDialogModule,
    MatTooltipModule,
    MatSnackBarModule,
    HttpClientModule
  ],
  providers: [
    EventRegistrationService,
    MailService,
    MatDialog
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
