import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

// angular material
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
// import widgets
import {MatCardModule, MatToolbarModule} from "@angular/material";

import {AppComponent} from './app.component';


@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatCardModule,
        MatToolbarModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
