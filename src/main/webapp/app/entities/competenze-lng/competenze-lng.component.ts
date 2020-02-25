import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompetenzeLng } from 'app/shared/model/competenze-lng.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CompetenzeLngService } from './competenze-lng.service';
import { CompetenzeLngDeleteDialogComponent } from './competenze-lng-delete-dialog.component';

@Component({
  selector: 'jhi-competenze-lng',
  templateUrl: './competenze-lng.component.html'
})
export class CompetenzeLngComponent implements OnInit, OnDestroy {
  competenzeLngs?: ICompetenzeLng[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected competenzeLngService: CompetenzeLngService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.competenzeLngService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICompetenzeLng[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInCompetenzeLngs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICompetenzeLng): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCompetenzeLngs(): void {
    this.eventSubscriber = this.eventManager.subscribe('competenzeLngListModification', () => this.loadPage());
  }

  delete(competenzeLng: ICompetenzeLng): void {
    const modalRef = this.modalService.open(CompetenzeLngDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.competenzeLng = competenzeLng;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICompetenzeLng[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/competenze-lng'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.competenzeLngs = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
