import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILingua, Lingua } from 'app/shared/model/lingua.model';
import { LinguaService } from './lingua.service';
import { LinguaComponent } from './lingua.component';
import { LinguaDetailComponent } from './lingua-detail.component';
import { LinguaUpdateComponent } from './lingua-update.component';

@Injectable({ providedIn: 'root' })
export class LinguaResolve implements Resolve<ILingua> {
  constructor(private service: LinguaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILingua> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lingua: HttpResponse<Lingua>) => {
          if (lingua.body) {
            return of(lingua.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Lingua());
  }
}

export const linguaRoute: Routes = [
  {
    path: '',
    component: LinguaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.lingua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LinguaDetailComponent,
    resolve: {
      lingua: LinguaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.lingua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LinguaUpdateComponent,
    resolve: {
      lingua: LinguaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.lingua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LinguaUpdateComponent,
    resolve: {
      lingua: LinguaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.lingua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
