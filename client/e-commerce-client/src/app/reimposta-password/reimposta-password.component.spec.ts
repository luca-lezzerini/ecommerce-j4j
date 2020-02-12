import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimpostaPasswordComponent } from './reimposta-password.component';

describe('ReimpostaPasswordComponent', () => {
  let component: ReimpostaPasswordComponent;
  let fixture: ComponentFixture<ReimpostaPasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReimpostaPasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimpostaPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
