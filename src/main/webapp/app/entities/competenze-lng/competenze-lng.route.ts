import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompetenzeLng, CompetenzeLng } from 'app/shared/model/competenze-lng.model';
import { CompetenzeLngService } from './competenze-lng.service';
import { CompetenzeLngComponent } from './competenze-lng.component';
import { CompetenzeLngDetailComponent } from './competenze-lng-detail.component';
import { CompetenzeLngUpdateComponent } from './competenze-lng-update.component';

@Injectable({ providedIn: 'root' })
export class CompetenzeLngResolve implements Resolve<ICompetenzeLng> {
  constructor(private service: CompetenzeLngService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompetenzeLng> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((competenzeLng: HttpResponse<CompetenzeLng>) => {
          if (competenzeLng.body) {
            return of(competenzeLng.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CompetenzeLng());
  }
}

export const competenzeLngRoute: Routes = [
  {
    path: '',
    component: CompetenzeLngComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jemoloRoosterApp.competenzeLng.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CompetenzeLngDetailComponent,
    resolve: {
      competenzeLng: CompetenzeLngResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.competenzeLng.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CompetenzeLngUpdateComponent,
    resolve: {
      competenzeLng: CompetenzeLngResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.competenzeLng.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CompetenzeLngUpdateComponent,
    resolve: {
      competenzeLng: CompetenzeLngResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.competenzeLng.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
