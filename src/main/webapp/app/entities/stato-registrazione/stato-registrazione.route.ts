import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStatoRegistrazione, StatoRegistrazione } from 'app/shared/model/stato-registrazione.model';
import { StatoRegistrazioneService } from './stato-registrazione.service';
import { StatoRegistrazioneComponent } from './stato-registrazione.component';
import { StatoRegistrazioneDetailComponent } from './stato-registrazione-detail.component';
import { StatoRegistrazioneUpdateComponent } from './stato-registrazione-update.component';

@Injectable({ providedIn: 'root' })
export class StatoRegistrazioneResolve implements Resolve<IStatoRegistrazione> {
  constructor(private service: StatoRegistrazioneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatoRegistrazione> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((statoRegistrazione: HttpResponse<StatoRegistrazione>) => {
          if (statoRegistrazione.body) {
            return of(statoRegistrazione.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StatoRegistrazione());
  }
}

export const statoRegistrazioneRoute: Routes = [
  {
    path: '',
    component: StatoRegistrazioneComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.statoRegistrazione.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StatoRegistrazioneDetailComponent,
    resolve: {
      statoRegistrazione: StatoRegistrazioneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.statoRegistrazione.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StatoRegistrazioneUpdateComponent,
    resolve: {
      statoRegistrazione: StatoRegistrazioneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.statoRegistrazione.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StatoRegistrazioneUpdateComponent,
    resolve: {
      statoRegistrazione: StatoRegistrazioneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.statoRegistrazione.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
