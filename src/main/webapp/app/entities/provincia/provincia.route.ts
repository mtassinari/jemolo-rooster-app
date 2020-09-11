import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProvincia, Provincia } from 'app/shared/model/provincia.model';
import { ProvinciaService } from './provincia.service';
import { ProvinciaComponent } from './provincia.component';
import { ProvinciaDetailComponent } from './provincia-detail.component';
import { ProvinciaUpdateComponent } from './provincia-update.component';

@Injectable({ providedIn: 'root' })
export class ProvinciaResolve implements Resolve<IProvincia> {
  constructor(private service: ProvinciaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProvincia> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((provincia: HttpResponse<Provincia>) => {
          if (provincia.body) {
            return of(provincia.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Provincia());
  }
}

export const provinciaRoute: Routes = [
  {
    path: '',
    component: ProvinciaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.provincia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProvinciaDetailComponent,
    resolve: {
      provincia: ProvinciaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.provincia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProvinciaUpdateComponent,
    resolve: {
      provincia: ProvinciaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.provincia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProvinciaUpdateComponent,
    resolve: {
      provincia: ProvinciaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.provincia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
