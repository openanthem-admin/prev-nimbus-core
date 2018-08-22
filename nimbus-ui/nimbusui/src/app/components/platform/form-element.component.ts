import { ValidationConstraint } from './../../shared/validationconstraints.enum';
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
import { Component, Input } from '@angular/core';
import { FormGroup, AbstractControlDirective, NgModel } from '@angular/forms';
import { Param } from '../../shared/param-state';
import { Message } from '../../shared/message';
import { ComponentTypes, ViewComponent } from '../../shared/param-annotations.enum';
import { BaseElement } from './base-element.component';
import { WebContentSvc } from './../../services/content-management.service';

var counter = 0;

/**
 * \@author Dinakar.Meda
 * \@author Sandeep.Mantha
 * \@whatItDoes 
 * 
 * \@howToUse 
 * 
 */
@Component({
    selector: 'nm-element',
    templateUrl: './form-element.component.html'
})

export class FormElement extends BaseElement {
    @Input() element: Param;
    @Input() form: FormGroup;
    @Input() elementCss: string;
    elemMessages: Message[];
    id: String = 'form-control' + counter++;
    componentStyle: string;
    componentTypes = ComponentTypes;
    viewComponent = ViewComponent;

    get isValid() {
        if (this.form.controls[this.element.config.code] != null) {
            return this.form.controls[this.element.config.code].valid;
        } else {
            return true;
        }
    }

    get isPristine() {
        return this.getPristine();
    }
    getPristine() {
        if (this.element.config.code.startsWith('{')) {
            return true;
        }
        if (this.form.controls[this.element.config.code] != null) {
            return this.form.controls[this.element.config.code].pristine;
        } else {
            return true;
        }
    }
    get elementMessages() {
        return this.getMessages();
    }
    getMessages() {
        this.elemMessages = [];
            if (this.element.message != null && this.element.message.length > 0) {
                this.elemMessages.push.apply(this.elemMessages, this.element.message);
            }
            if (!this.getPristine()) {
                this.getErrors();
            }
        return this.elemMessages;
    }

    get showMessages() {
        return (this.elemMessages != null && this.elemMessages.length > 0);
    }

    constructor(private wcsv: WebContentSvc) { 
        super(wcsv);
    }

    getErrorStyles() {
        if (this.showErrors) {
            return 'alert alert-danger';
        } else {
            return '';
        }
    }

    get showErrors() {
        return (!this.isPristine && !this.isValid);
    }

    getComponentClass() {
        let componentClass: string[] = [];
        componentClass.push('form-group');
        if (this.element.config.uiStyles && this.element.config.uiStyles.attributes &&
            this.element.config.uiStyles.attributes.cssClass && this.element.config.uiStyles.attributes.cssClass !== '') {
                componentClass.push(this.element.config.uiStyles.attributes.cssClass);
        } else {
            componentClass.push(this.elementCss);
        }
        
        // Error Styles
        componentClass.push(this.getErrorStyles());

        return componentClass;
    }

    ngOnInit() {
        super.ngOnInit();
        this.updatePositionWithNoLabel();        
        // if (this.element.config.uiStyles && this.element.config.uiStyles.attributes.controlId !== null) {
        //     if (Number(this.element.config.uiStyles.attributes.controlId) % 2 === 0) {
        //         this.elementCss = this.elementCss + ' even';
        //     } else {
        //         this.elementCss = this.elementCss + ' odd';
        //     }
        // }
    }

    getElementStyle() {
        if (this.element.config.uiStyles != null && this.element.config.uiStyles.attributes.alias === 'MultiSelectCard') {
            return 'col-lg-12 col-md-6';
        } else {
            return '';
        }
    }
    
    getErrors() {
        if (this.form.controls[this.element.config.code].invalid) {
            if (this.element.config.validation) {
                this.element.config.validation.constraints.forEach(validator => {
                    // Defaults
                    // TODO - Consider moving this logic to a lookup service.
                    if (this.form.controls[this.element.config.code].errors.required && validator.name === ValidationConstraint._notNull.value) {
                        this.addErrorMessages(validator.attribute.message ? validator.attribute.message : 'Field is required.');
                    }
                    if (this.form.controls[this.element.config.code].errors.pattern && validator.name === ValidationConstraint._pattern.value) {
                        this.addErrorMessages(validator.attribute.message ? validator.attribute.message : 'Field is required.');
                    }
                    if (this.form.controls[this.element.config.code].errors.minMaxSelection && validator.name === ValidationConstraint._size.value) {
                        this.addErrorMessages(validator.attribute.message ? validator.attribute.message : 'Field is required.');
                    }
                    if (this.form.controls[this.element.config.code].errors.isNumber) {
                        //if (!this.checkforCustomMessages(validator, errors))
                        this.addErrorMessages('Value must be a number.');
                    }
                    //}
                });
            }
        }
    }

    addErrorMessages(errorText: string) {
        let errorMessage: Message, summary: string;
        errorMessage = new Message();
        errorMessage.context = 'INLINE';
        errorMessage.life = 10000;
        errorMessage.messageArray.push({ severity: 'error', summary: summary, detail: errorText });
        this.elemMessages.push(errorMessage);
    }

    ngModelState(ngm: AbstractControlDirective): string {
        let ret = ngm instanceof NgModel ? 'name: ${ngm.name};  ' : '';
        return ret + 'touched: ${ngm.touched};  pristine: ${ngm.pristine};  valid: ${ngm.valid};  errors: ${JSON.stringify(ngm.errors)};  value: ${JSON.stringify(ngm.value)}';
    }
}
