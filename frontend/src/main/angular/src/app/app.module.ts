import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule,ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
// angular material
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
// import widgets
import {MatCardModule, MatToolbarModule, MatStepperModule, MatFormFieldModule, MatButtonModule, MatInputModule, MatSelectModule} from "@angular/material";

import {AppComponent} from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { AppRoutingModule } from './app-routing.module';
import {EventcodeService} from "./eventcode.service";


@NgModule({
    declarations: [
        AppComponent,
        RegistrationComponent
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
        HttpClientModule
    ],
    providers: [
        EventcodeService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
