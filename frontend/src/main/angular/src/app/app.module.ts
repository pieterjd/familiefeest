import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule,ReactiveFormsModule} from "@angular/forms";
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
    MatTooltipModule
} from "@angular/material";

import {AppComponent} from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { AppRoutingModule } from './app-routing.module';
import {EventCodeService} from "./eventcode.service";
import { EventcodeCheckerComponent } from './eventcode-checker/eventcode-checker.component';
import {AddMenuDialog, MenulistComponent} from './menulist/menulist.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';


@NgModule({
    declarations: [
        AppComponent,
        RegistrationComponent,
        EventcodeCheckerComponent,
        MenulistComponent,
        AddMenuDialog,
        ConfirmationComponent
    ],
    entryComponents: [
        AddMenuDialog
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
        HttpClientModule
    ],
    providers: [
        EventCodeService,
        MatDialog
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
