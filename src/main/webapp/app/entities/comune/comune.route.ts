import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComune, Comune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';
import { ComuneComponent } from './comune.component';
import { ComuneDetailComponent } from './comune-detail.component';
import { ComuneUpdateComponent } from './comune-update.component';

@Injectable({ providedIn: 'root' })
export class ComuneResolve implements Resolve<IComune> {
  constructor(private service: ComuneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComune> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((comune: HttpResponse<Comune>) => {
          if (comune.body) {
            return of(comune.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Comune());
  }
}

export const comuneRoute: Routes = [
  {
    path: '',
    component: ComuneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.comune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ComuneDetailComponent,
    resolve: {
      comune: ComuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.comune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ComuneUpdateComponent,
    resolve: {
      comune: ComuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.comune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ComuneUpdateComponent,
    resolve: {
      comune: ComuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.comune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
