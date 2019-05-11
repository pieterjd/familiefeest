import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

// angular material
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
// import widgets
import {MatCardModule, MatToolbarModule, MatStepperModule, MatFormFieldModule} from "@angular/material";

import {AppComponent} from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { AppRoutingModule } from './app-routing.module';


@NgModule({
    declarations: [
        AppComponent,
        RegistrationComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatCardModule,
        MatToolbarModule,
        MatStepperModule,
        MatFormFieldModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
