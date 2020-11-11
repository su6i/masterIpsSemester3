import { NgModule                 } from '@angular/core'                                                                 ;
import { Routes, RouterModule     } from '@angular/router'                                                               ;
import { HttpModule               } from '@angular/http'                                                                 ;

import { HomeComponent            } from './components/home/home.component'                                              ;
import { LoginComponent           } from './components/login/login.component'                                            ;
import { TypesComponent           } from './components/types/types.component'                                            ;
import { NavbarComponent          } from './components/navbar/navbar.component'                                          ;
import { ProductComponent         } from './components/product/product.component'                                        ;
import { ProductDetailsComponent  } from './components/product/product-details/product-details.component'                                        ;
import { ProfileComponent         } from './components/profile/profile.component'                                        ;
import { RegisterComponent        } from './components/register/register.component'                                      ;
import { DashboardComponent       } from './components/dashboard/dashboard.component'                                    ;
import { PageNotFoundComponent    } from './components/page-not-found/page-not-found.component'                          ;
import { CategoryMainComponent    } from './components/category/category-main/category-main.component'                   ;
import { CategorySubComponent     } from './components/category/category-sub/category-sub.component'                     ;
import { CommentComponent         } from './components/comment/comment.component'                                        ;
import { CommentFormComponent     } from './components/comment/comment-form/comment-form.component'                      ;
import { CommentFormListComponent } from './components/comment/comment-form-list/comment-form-list.component'            ;
import { OrderComponent           } from './components/order/order.component'            ;
import { OrderDetailsComponent    } from './components/order/order-details/order-details.component'            ;


import { AuthGuard                } from './guards/auth.guard'                                                           ;


const routes: Routes = [
  { path: ''                             ,  component: HomeComponent                                                     },
  { path: 'products'                     ,  component: ProductComponent                                                  },
  { path: 'products/:pid'                ,  component: ProductDetailsComponent                                                  },
  { path: 'categories'                   ,  component: CategoryMainComponent                                             },
  { path: 'categories/:category/:types'  ,  component: TypesComponent                                                    },
  { path: 'categories/:category'         ,  component: CategorySubComponent                                              },
  { path: 'login'                        ,  component: LoginComponent                                                    },
  { path: 'navbar'                       ,  component: NavbarComponent                                                   },
  { path: 'register'                     ,  component: RegisterComponent                                                 },
  { path: 'dashboard'                    ,  component: DashboardComponent, canActivate:[AuthGuard]                       },
  { path: 'comment'                      ,  component: CommentComponent,   canActivate:[AuthGuard]                       },
  { path: 'profile'                      ,  component: ProfileComponent,   canActivate:[AuthGuard]                       },
  { path: 'orders'                       ,  component: OrderComponent,   canActivate:[AuthGuard]                       },
  { path: 'orders/:oid'                  ,  component: OrderDetailsComponent,   canActivate:[AuthGuard]                       },
  { path: '**'                           ,  component: PageNotFoundComponent                                             }  // Wildcard route for a 404 page

];

@NgModule({
  imports: [HttpModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
