import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { LinguaComponent } from './lingua.component';
import { LinguaDetailComponent } from './lingua-detail.component';
import { LinguaUpdateComponent } from './lingua-update.component';
import { LinguaDeleteDialogComponent } from './lingua-delete-dialog.component';
import { linguaRoute } from './lingua.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(linguaRoute)],
  declarations: [LinguaComponent, LinguaDetailComponent, LinguaUpdateComponent, LinguaDeleteDialogComponent],
  entryComponents: [LinguaDeleteDialogComponent],
})
export class JemoloRoosterAppLinguaModule {}
