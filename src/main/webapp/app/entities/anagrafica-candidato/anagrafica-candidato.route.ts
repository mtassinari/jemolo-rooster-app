import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAnagraficaCandidato, AnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from './anagrafica-candidato.service';
import { AnagraficaCandidatoComponent } from './anagrafica-candidato.component';
import { AnagraficaCandidatoDetailComponent } from './anagrafica-candidato-detail.component';
import { AnagraficaCandidatoUpdateComponent } from './anagrafica-candidato-update.component';

@Injectable({ providedIn: 'root' })
export class AnagraficaCandidatoResolve implements Resolve<IAnagraficaCandidato> {
  constructor(private service: AnagraficaCandidatoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnagraficaCandidato> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((anagraficaCandidato: HttpResponse<AnagraficaCandidato>) => {
          if (anagraficaCandidato.body) {
            return of(anagraficaCandidato.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AnagraficaCandidato());
  }
}

export const anagraficaCandidatoRoute: Routes = [
  {
    path: '',
    component: AnagraficaCandidatoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jemoloRoosterApp.anagraficaCandidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AnagraficaCandidatoDetailComponent,
    resolve: {
      anagraficaCandidato: AnagraficaCandidatoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.anagraficaCandidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AnagraficaCandidatoUpdateComponent,
    resolve: {
      anagraficaCandidato: AnagraficaCandidatoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.anagraficaCandidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AnagraficaCandidatoUpdateComponent,
    resolve: {
      anagraficaCandidato: AnagraficaCandidatoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.anagraficaCandidato.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
