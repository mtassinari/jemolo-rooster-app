import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAmbitoCompetenza, AmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';
import { AmbitoCompetenzaService } from './ambito-competenza.service';
import { AmbitoCompetenzaComponent } from './ambito-competenza.component';
import { AmbitoCompetenzaDetailComponent } from './ambito-competenza-detail.component';
import { AmbitoCompetenzaUpdateComponent } from './ambito-competenza-update.component';

@Injectable({ providedIn: 'root' })
export class AmbitoCompetenzaResolve implements Resolve<IAmbitoCompetenza> {
  constructor(private service: AmbitoCompetenzaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAmbitoCompetenza> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ambitoCompetenza: HttpResponse<AmbitoCompetenza>) => {
          if (ambitoCompetenza.body) {
            return of(ambitoCompetenza.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AmbitoCompetenza());
  }
}

export const ambitoCompetenzaRoute: Routes = [
  {
    path: '',
    component: AmbitoCompetenzaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.ambitoCompetenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AmbitoCompetenzaDetailComponent,
    resolve: {
      ambitoCompetenza: AmbitoCompetenzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.ambitoCompetenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AmbitoCompetenzaUpdateComponent,
    resolve: {
      ambitoCompetenza: AmbitoCompetenzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.ambitoCompetenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AmbitoCompetenzaUpdateComponent,
    resolve: {
      ambitoCompetenza: AmbitoCompetenzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.ambitoCompetenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
