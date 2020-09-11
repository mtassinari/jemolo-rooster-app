import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICandidato, Candidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './candidato.service';
import { CandidatoComponent } from './candidato.component';
import { CandidatoDetailComponent } from './candidato-detail.component';
import { CandidatoUpdateComponent } from './candidato-update.component';

@Injectable({ providedIn: 'root' })
export class CandidatoResolve implements Resolve<ICandidato> {
  constructor(private service: CandidatoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICandidato> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((candidato: HttpResponse<Candidato>) => {
          if (candidato.body) {
            return of(candidato.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Candidato());
  }
}

export const candidatoRoute: Routes = [
  {
    path: '',
    component: CandidatoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jemoloRoosterApp.candidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CandidatoDetailComponent,
    resolve: {
      candidato: CandidatoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.candidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CandidatoUpdateComponent,
    resolve: {
      candidato: CandidatoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.candidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CandidatoUpdateComponent,
    resolve: {
      candidato: CandidatoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.candidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
