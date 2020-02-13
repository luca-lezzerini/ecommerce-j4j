import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Incrementatore06Component } from './incrementatore06.component';

describe('Incrementatore06Component', () => {
  let component: Incrementatore06Component;
  let fixture: ComponentFixture<Incrementatore06Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Incrementatore06Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Incrementatore06Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
