import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompetenza, Competenza } from 'app/shared/model/competenza.model';
import { CompetenzaService } from './competenza.service';
import { CompetenzaComponent } from './competenza.component';
import { CompetenzaDetailComponent } from './competenza-detail.component';
import { CompetenzaUpdateComponent } from './competenza-update.component';

@Injectable({ providedIn: 'root' })
export class CompetenzaResolve implements Resolve<ICompetenza> {
  constructor(private service: CompetenzaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompetenza> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((competenza: HttpResponse<Competenza>) => {
          if (competenza.body) {
            return of(competenza.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Competenza());
  }
}

export const competenzaRoute: Routes = [
  {
    path: '',
    component: CompetenzaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jemoloRoosterApp.competenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CompetenzaDetailComponent,
    resolve: {
      competenza: CompetenzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.competenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CompetenzaUpdateComponent,
    resolve: {
      competenza: CompetenzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.competenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CompetenzaUpdateComponent,
    resolve: {
      competenza: CompetenzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.competenza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
