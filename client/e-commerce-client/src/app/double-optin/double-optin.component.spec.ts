import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoubleOptinComponent } from './double-optin.component';

describe('DoubleOptinComponent', () => {
  let component: DoubleOptinComponent;
  let fixture: ComponentFixture<DoubleOptinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoubleOptinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoubleOptinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
