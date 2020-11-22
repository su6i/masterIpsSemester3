import { NgModule                } from '@angular/core'                                                           ;
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule            } from "@angular/common"                                                         ;
import { JwtModule               } from '@auth0/angular-jwt'                                                                                             ;
import { HttpClientModule        } from '@angular/common/http'                                                    ;
import { BrowserModule           } from '@angular/platform-browser'                                               ;
import { MatInputModule          } from '@angular/material/input'                                                 ;
import { MatCardModule           } from '@angular/material/card'                                                  ;
import { MatButtonModule         } from '@angular/material/button'                                                ;
import { MatToolbarModule        } from '@angular/material/toolbar'                                               ;
import { MatExpansionModule      } from '@angular/material/expansion'                                             ;
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'                                    ;
import { MatIconModule           } from '@angular/material/icon';
import { MatAutocompleteModule   } from '@angular/material/autocomplete';
import { MatChipsModule          } from '@angular/material/chips';
import { MatFormFieldModule      } from '@angular/material/form-field';


import { AppComponent                                 } from './app.component'                                                         ;
import { AppRoutingModule                             } from './app-routing.module'                                                    ;
import { HomeComponent                                } from './components/home/home.component'                                        ;
import { TypesComponent                               } from './components/types/types.component'                                      ;
import { LoginComponent                               } from './components/login/login.component'                                      ;
import { OrderComponent                               } from './components/order/order.component';
import { NavbarComponent                              } from './components/navbar/navbar.component'                                    ;
import { ProductComponent                             } from './components/product/product.component'                                  ;
import { ProfileComponent                             } from './components/profile/profile.component'                                  ;
import { CommentComponent                             } from './components/comment/comment.component'                                  ;
import { RegisterComponent                            } from './components/register/register.component'                                ;
import { DashboardComponent                           } from './components/dashboard/dashboard.component'                              ;
import { SearchBarComponent                           } from './components/search-bar/search-bar.component'                                                                                        ;
import { LoginEmailComponent                          } from './components/login/login-email/login-email.component'                                                           ;
import { CommentFormComponent                         } from './components/comment/comment-form/comment-form.component'                ;
import { CategorySubComponent                         } from './components/category/category-sub/category-sub.component'               ;
import { OrderDetailsComponent                        } from './components/order/order-details/order-details.component';
import { PageNotFoundComponent                        } from './components/page-not-found/page-not-found.component'                    ;
import { CategoryMainComponent                        } from './components/category/category-main/category-main.component'             ;
import { CommentFormListComponent                     } from './components/comment/comment-form-list/comment-form-list.component'      ;
import { RegisterFlashMessageComponent                } from './components/register/register-flash-message/register-flash-message.component'                                  ;
import { RegisterValidationUsedngIfDirectiveComponent } from './components/register/register-validation-usedng-if-directive/register-validation-usedng-if-directive.component';
import { ProductDetailsComponent } from './components/product/product-details/product-details.component';


import { AuthGuard                                    } from './guards/auth.guard'                                                                                            ;
import { AuthService                                  } from './services/auth.service'                                                                                        ;
import { ValidateService                              } from './services/validate.service'                                                                                    ;
import { FlashMessagesModule                          } from 'angular2-flash-messages';
import { DataService } from './services/data.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TypesComponent,
    LoginComponent,
    OrderComponent,
    NavbarComponent,
    CommentComponent,
    ProductComponent,
    ProfileComponent,
    RegisterComponent,
    DashboardComponent,
    LoginEmailComponent,
    CommentFormComponent,
    CategorySubComponent,
    CategoryMainComponent,
    PageNotFoundComponent,
    OrderDetailsComponent,
    ProductDetailsComponent,
    CommentFormListComponent,
    RegisterFlashMessageComponent,
    RegisterValidationUsedngIfDirectiveComponent,
    SearchBarComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatChipsModule,
    MatButtonModule,
    MatToolbarModule,
    MatExpansionModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    FlashMessagesModule.forRoot(),
    BrowserAnimationsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem("id_token");
        },
        allowedDomains: ["localhost:8888"],
        disallowedRoutes: ['http://localhost:8888/login'],
      },
    }),
  ],
  providers: [ValidateService, AuthService, AuthGuard, DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
export function tokenGetter() {
  return localStorage.getItem("id_token");
}
