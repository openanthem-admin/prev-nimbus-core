import { InPlaceEditorComponent } from './components/platform/form/elements/inplace-editor.component';
import { TextArea } from './components/platform/form/elements/textarea.component';
import { MultiSelectListBox } from './components/platform/form/elements/multi-select-listbox.component';
import { BreadcrumbComponent } from './components/platform/breadcrumb/breadcrumb.component';
import { BreadcrumbService } from './components/platform/breadcrumb/breadcrumb.service';
import { HomeLayoutCmp } from './components/home/home-layout.component';
import { DomainLayoutCmp } from './components/domain/domain-layout.component';
import { StyleGuideCmp } from './styleguide/style-guide.component';
import { MainLayoutCmp } from './components/home/main-layout.component';
import { LoginLayoutCmp } from './components/login/login-layout.component';
import { OrderablePickList } from './components/platform/form/elements/picklist.component';
import { PageService } from './services/page.service';
import { PageNotfoundComponent } from './components/platform/content/page-notfound.component';
import { PageContent } from './components/platform/content/page-content.component';
import { GridService } from './services/grid.service';
import { GridMouseEventDirective } from './directives/gridhover.directive';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule, BrowserXhr } from '@angular/http';
import { ReactiveFormsModule }  from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { DataTableModule, SharedModule, OverlayPanelModule } from 'primeng/primeng';
import { PickListModule, DragDropModule } from 'primeng/primeng';
import {FileUpload, FileUploadModule} from 'primeng/primeng';
import { SortableComponentDirective, SortableContainerDirective } from './directives/sortable-dragdrop.directive';
import { NavLinkRouter } from './directives/nav-link-router.directive';
import { ListboxModule, DialogModule } from 'primeng/primeng';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// Platform Imports
// Components
import { LayoutService } from './services/layout.service';
import { ContentContainer } from './components/platform/content/content-container.component';
import { Switch } from './components/platform/form/elements/switch.component';
import { BaseElement } from './components/platform/base-element.component';
import { AppComponent }  from './app.component';
import { Tile }  from './components/platform/tile.component';
import { Section } from './components/platform/section.component';
import { Header } from './components/platform/content/header.component';
import { Form }  from './components/platform/form.component';
import { FormElement }  from './components/platform/form-element.component';
import { Image } from './components/platform/image.component';
import { Paragraph } from './components/platform/content/paragraph.component';
import { Value } from './components/platform/form/elements/value.component';
import { ComboBox } from './components/platform/form/elements/combobox.component';
import { RadioButton } from './components/platform/form/elements/radio.component';
import { InputText } from './components/platform/form/elements/textbox.component';
import { DateControl } from './components/platform/form/elements/date.component';
import { CheckBoxGroup } from './components/platform/form/elements/checkbox-group.component';
import { MultiselectCard } from './components/platform/form/elements/multi-select-card.component';
import { ActionDropdown } from './components/platform/form/elements/action-dropdown.component';
import { GridContainer } from './components/platform/grid/grid-container.component';
import { InfiniteScrollGrid } from './components/platform/grid/grid.component';
import { Link } from './components/platform/link.component';
import { DisableFormControl } from './components/platform/form/elements/control-disable.directive';
import { Menu } from './components/platform/menu.component';
import { FlowWrapper } from './components/platform/content/flow-wrapper.component';
import { SubHeaderCmp } from './components/platform/sub-header.component';
import { FieldValue } from './components/platform/content/field-value.component';
import { StaticText } from './components/platform/content/static-content.component';
import { CardDetailsComponent } from './components/platform/card/card-details.component';
import { CardDetailsFieldComponent } from './components/platform/card/card-details-field.component';
import { CardDetailsGrid } from './components/platform/card/card-details-grid.component';
import { Accordion } from './components/platform/accordion.component';
import { AccordionGroup } from './components/platform/accordion-group.component';
import { FrmGroupCmp } from './components/platform/form-group.component';
import { MultiselectDropdownModule } from './components/platform/form/elements/multi-select-dropdown.component';
import { Button } from './components/platform/form/elements/button.component';
import { ButtonGroup } from './components/platform/form/elements/button-group.component';
import { FilterButton } from './components/platform/form/elements/filter-button.component';
import { DragDropConfig } from './shared/app-config.interface';
import { CheckBox } from './components/platform/form/elements/checkbox.component';
import { DomainFlowCmp } from './components/domain/domain-flow.component';
import { FileUploadComponent } from './components/platform/fileupload/file-upload.component';
import { Modal } from './components/platform/modal/modal.component';
import { TooltipComponent } from './components/platform/tooltip/tooltip.component';
import { HeaderGlobal } from './components/platform/header/header-global.component';
import { FooterGlobal } from './components/platform/footer/footer-global.component';
// Services
import { WebContentSvc } from './services/content-management.service';
import { STOMPStatusComponent } from './services/stomp-status.component';
import { DragDropService, DragDropSortableService} from './services/dragdrop.service';
import { AuthenticationService } from './services/authentication.service';
// Routes
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { HttpClient } from './services/httpclient.service';
import { CustomBrowserXhr } from './custom.browserxhr';

// Declarations
import { LoginCmp } from './components/login/login.component';
import { LandingPage } from './components/login/auth-landingpage';
import { KeysPipe } from './pipes/app.pipe';
import { LinkPipe } from './pipes/link.pipe';


@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        AppRoutingModule,
        HttpModule,
        FormsModule,
        MultiselectDropdownModule,
        DataTableModule,
        OverlayPanelModule,
        PickListModule,
        DragDropModule,
        ListboxModule,
        SharedModule,
        FileUploadModule,
        DialogModule,
        BrowserModule,
        BrowserAnimationsModule,
    ],
    declarations: [ AppComponent, STOMPStatusComponent, FlowWrapper, PageContent, PageNotfoundComponent, StaticText,
        Tile, Section, Header, Form, FormElement, InputText, ComboBox, RadioButton, DateControl, CheckBoxGroup,
        InPlaceEditorComponent, Paragraph, Value, Image, BaseElement, 
        MultiselectCard, Link, Menu, CardDetailsComponent, CardDetailsFieldComponent, CardDetailsGrid, FieldValue,
        AccordionGroup, Accordion, FrmGroupCmp, Button, ButtonGroup, FilterButton, OrderablePickList,
        STOMPStatusComponent, GridContainer, InfiniteScrollGrid, SubHeaderCmp, DisableFormControl, TextArea, LandingPage,
        LayoutService,ContentContainer,Switch,
        DomainFlowCmp,HeaderGlobal,FooterGlobal,
        BreadcrumbComponent, NavLinkRouter,
        Modal, ActionDropdown,
        GridMouseEventDirective, SortableContainerDirective, SortableComponentDirective,
        HomeLayoutCmp, MainLayoutCmp, DomainLayoutCmp, LoginCmp, LoginLayoutCmp, StyleGuideCmp, KeysPipe, LinkPipe, MultiSelectListBox, 
        CheckBox, FileUploadComponent, BreadcrumbComponent, TooltipComponent

    ],
    entryComponents: [ FlowWrapper, PageContent, PageNotfoundComponent, LoginCmp, MainLayoutCmp, HomeLayoutCmp],
    providers: [ PageService, WebContentSvc,
         HttpClient, { provide: BrowserXhr, useClass: CustomBrowserXhr },
         { provide: LocationStrategy, useClass: HashLocationStrategy }, GridService, DragDropService, DragDropSortableService, DragDropConfig,
         AuthenticationService, BreadcrumbService ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }