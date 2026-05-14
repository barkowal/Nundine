import { Routes } from '@angular/router';
import { HomePage } from './pages/home-page/home-page';
import { canActivateAuthRole } from './auth.guard';
import { ForbiddenPage } from './pages/forbidden-page/forbidden-page';
import { NotfoundPage } from './pages/notfound-page/notfound-page';
import { InformationPage } from './pages/information-page/information-page';
import { ShopPage } from './pages/shop-page/shop-page';
import { MyStorePage } from './pages/my-store-page/my-store-page';
import { ForestPage } from './pages/forest-page/forest-page';

export const routes: Routes = [
  {
    path: "",
    component: HomePage,
  },
  {
    path: "inventory",
    component: HomePage,
    canActivate: [canActivateAuthRole],
    data: { role: "user" },
  },
  {
    path: 'shop',
    component: ShopPage
  },
  {
    path: 'info',
    component: InformationPage
  },
  {
    path: "mystore",
    component: MyStorePage,
    canActivate: [canActivateAuthRole],
    data: { role: "seller" },
  },
  {
    path: "forest",
    component: ForestPage,
  },
  {
    path: 'forbidden',
    component: ForbiddenPage
  },
  {
    path: '**',
    component: NotfoundPage
  }
];
