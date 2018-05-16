/**
 * @license
 * Copyright 2016-2018 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
'use strict';
import { Component, ViewEncapsulation, Inject } from '@angular/core';
import { HostListener } from '@angular/core/src/metadata/directives';
import { WebContentSvc } from './services/content-management.service';
import { ServiceConstants } from './services/service.constants';
import { DOCUMENT } from '@angular/platform-browser';
import * as moment from 'moment';

/**
 * \@author Dinakar.Meda
 * \@author Sandeep.Mantha
 * \@whatItDoes 
 * 
 * \@howToUse 
 * 
 */
@Component({
    selector: 'app-root',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './app.component.html'
})

export class AppComponent {

    constructor(@Inject(DOCUMENT) private document: any) {
    }

    ngOnInit() {
        ServiceConstants.STOPGAP_APP_HOST = this.document.location.hostname;
        ServiceConstants.STOPGAP_APP_PORT = this.document.location.port;
        ServiceConstants.APP_CONTEXT = this.document.location.pathname.split('/').splice(1, 1);
        ServiceConstants.LOCALE_LANGUAGE = "en-US"; //TODO This locale should be read dynamically. Currently defaulting to en-US
        ServiceConstants.STOPGAP_APP_PROTOCOL = this.document.location.protocol;
    }

}
