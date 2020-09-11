import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAllegato, Allegato } from 'app/shared/model/allegato.model';
import { AllegatoService } from './allegato.service';
import { AllegatoComponent } from './allegato.component';
import { AllegatoDetailComponent } from './allegato-detail.component';
import { AllegatoUpdateComponent } from './allegato-update.component';

@Injectable({ providedIn: 'root' })
export class AllegatoResolve implements Resolve<IAllegato> {
  constructor(private service: AllegatoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAllegato> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((allegato: HttpResponse<Allegato>) => {
          if (allegato.body) {
            return of(allegato.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Allegato());
  }
}

export const allegatoRoute: Routes = [
  {
    path: '',
    component: AllegatoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.allegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AllegatoDetailComponent,
    resolve: {
      allegato: AllegatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.allegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AllegatoUpdateComponent,
    resolve: {
      allegato: AllegatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.allegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AllegatoUpdateComponent,
    resolve: {
      allegato: AllegatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.allegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
