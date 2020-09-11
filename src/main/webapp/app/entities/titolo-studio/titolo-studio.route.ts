import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITitoloStudio, TitoloStudio } from 'app/shared/model/titolo-studio.model';
import { TitoloStudioService } from './titolo-studio.service';
import { TitoloStudioComponent } from './titolo-studio.component';
import { TitoloStudioDetailComponent } from './titolo-studio-detail.component';
import { TitoloStudioUpdateComponent } from './titolo-studio-update.component';

@Injectable({ providedIn: 'root' })
export class TitoloStudioResolve implements Resolve<ITitoloStudio> {
  constructor(private service: TitoloStudioService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITitoloStudio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((titoloStudio: HttpResponse<TitoloStudio>) => {
          if (titoloStudio.body) {
            return of(titoloStudio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TitoloStudio());
  }
}

export const titoloStudioRoute: Routes = [
  {
    path: '',
    component: TitoloStudioComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.titoloStudio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TitoloStudioDetailComponent,
    resolve: {
      titoloStudio: TitoloStudioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.titoloStudio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TitoloStudioUpdateComponent,
    resolve: {
      titoloStudio: TitoloStudioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.titoloStudio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TitoloStudioUpdateComponent,
    resolve: {
      titoloStudio: TitoloStudioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jemoloRoosterApp.titoloStudio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
