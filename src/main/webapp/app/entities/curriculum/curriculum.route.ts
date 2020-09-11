import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICurriculum, Curriculum } from 'app/shared/model/curriculum.model';
import { CurriculumService } from './curriculum.service';
import { CurriculumComponent } from './curriculum.component';
import { CurriculumDetailComponent } from './curriculum-detail.component';
import { CurriculumUpdateComponent } from './curriculum-update.component';

@Injectable({ providedIn: 'root' })
export class CurriculumResolve implements Resolve<ICurriculum> {
  constructor(private service: CurriculumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICurriculum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((curriculum: HttpResponse<Curriculum>) => {
          if (curriculum.body) {
            return of(curriculum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Curriculum());
  }
}

export const curriculumRoute: Routes = [
  {
    path: '',
    component: CurriculumComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jemoloRoosterApp.curriculum.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CurriculumDetailComponent,
    resolve: {
      curriculum: CurriculumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.curriculum.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CurriculumUpdateComponent,
    resolve: {
      curriculum: CurriculumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.curriculum.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CurriculumUpdateComponent,
    resolve: {
      curriculum: CurriculumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jemoloRoosterApp.curriculum.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
