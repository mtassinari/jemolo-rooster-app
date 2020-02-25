import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { CurriculumComponent } from './curriculum.component';
import { CurriculumDetailComponent } from './curriculum-detail.component';
import { CurriculumUpdateComponent } from './curriculum-update.component';
import { CurriculumDeleteDialogComponent } from './curriculum-delete-dialog.component';
import { curriculumRoute } from './curriculum.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(curriculumRoute)],
  declarations: [CurriculumComponent, CurriculumDetailComponent, CurriculumUpdateComponent, CurriculumDeleteDialogComponent],
  entryComponents: [CurriculumDeleteDialogComponent]
})
export class JemoloRoosterAppCurriculumModule {}
